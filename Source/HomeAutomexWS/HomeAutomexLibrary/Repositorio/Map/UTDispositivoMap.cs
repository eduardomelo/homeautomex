using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HomeAutomexLibrary.Entidade;

namespace HomeAutomexLibrary.Repositorio.Map
{
    public class UTDispositivoMap : EntityTypeConfiguration<UTDispositivo>
    {
        public UTDispositivoMap()
        {

            ToTable("UT_DISPOSITIVO");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_UT_DISPOSITIVO")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.Descricao).HasColumnName("DESCRICAO").IsRequired();
            Property(e => e.Nome).HasColumnName("USUARIO").IsRequired();
            Property(e => e.UT_utilizacao).HasColumnName("DT_ULTILIZACAO");
            Property(e => e.status).HasColumnName("STATUS");


            Ignore(e => e.DataCadastro);
            Ignore(e => e.DataAlteracao);
            Ignore(e => e.DataExclusao);
        }

    }
}
